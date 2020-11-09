package com.nutn.utm.ut;

import com.nutn.utm.UtmApplication;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.repository.FlightPlanRepository;
import com.nutn.utm.service.AccountService;
import com.nutn.utm.service.FlightPlanFeasibilityValidator;
import com.nutn.utm.service.FlightPlanService;
import com.nutn.utm.service.UavService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = UtmApplication.class)
@ActiveProfiles("dev")
@Sql(scripts = {"classpath:clear.sql", "classpath:testData.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class FlightPlanServiceTest {

    @MockBean
    FlightPlanRepository flightPlanRepository;

    @Autowired
    private FlightPlanService flightPlanService;

    @MockBean
    private FlightPlanFeasibilityValidator flightPlanFeasibilityValidator;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UavService uavService;

    private final String PILOT_ACCOUNT = "ShawnWu";

    private final long ACCOUNT_ID = 1101;


    @Test
    public void should_call_validateFeasibility_once_when() {
        String executionDate = "2090-03-31";
        String startTime = "20:59";
        String endTime = "21:00";
        String macAddress = "0000000018021025";
        int maxFlyingAltitude = 30;
        Double[][] coordinate = {
                {120.14236450195312, 23.161194613746826}, {120.1348114013672, 23.22935799291749},
                {120.21102905273436, 23.174451324926217}, {120.21377563476562, 23.240715176105464}
        };
        FlightPlanWayPointsDto flightPlanWayPointsDto = new FlightPlanWayPointsDto(coordinate);
        FlightPlanApplicationForm flightPlanApplicationForm = FlightPlanApplicationForm.builder()
                .executionDate(executionDate)
                .startTime(startTime)
                .endTime(endTime)
                .flightPlanWayPointsDto(flightPlanWayPointsDto)
                .maxFlyingAltitude(maxFlyingAltitude)
                .macAddress(macAddress)
                .build();
        flightPlanFeasibilityValidator.validateFeasibility(flightPlanApplicationForm);
        verify(flightPlanFeasibilityValidator, times(1)).validateFeasibility(flightPlanApplicationForm);
    }

    @Test
    public void should_return_new_flight_plan_when_apply_flight_plan() {
        String executionDate = "2090-03-31";
        String startTime = "20:59";
        String endTime = "21:00";
        String macAddress = "0000000018021025";
        int maxFlyingAltitude = 30;
        Double[][] coordinate = {
                {120.14236450195312, 23.161194613746826}, {120.1348114013672, 23.22935799291749},
                {120.21102905273436, 23.174451324926217}, {120.21377563476562, 23.240715176105464}
        };
        FlightPlanWayPointsDto flightPlanWayPointsDto = new FlightPlanWayPointsDto(coordinate);
        long planId = 65;

        FlightPlan stubFlightPlan = mock(FlightPlan.class);
        when(stubFlightPlan.getId()).thenReturn(planId);

        Uav uav = mock(Uav.class);
        when(uavService.checkIfPilotOwnsThisUav(anyLong(), anyString())).thenReturn(uav);
        when(flightPlanRepository.save(any(FlightPlan.class)))
                .thenReturn(stubFlightPlan);

        FlightPlanApplicationForm planFormDTO = FlightPlanApplicationForm.builder()
                .executionDate(executionDate)
                .startTime(startTime)
                .endTime(endTime)
                .flightPlanWayPointsDto(flightPlanWayPointsDto)
                .maxFlyingAltitude(maxFlyingAltitude)
                .macAddress(macAddress)
                .build();

        FlightPlan plan = flightPlanService.applyFlightPlan(ACCOUNT_ID, planFormDTO);

        Assert.assertEquals(plan.getId(), planId);
    }


    @Test
    public void should_get_one_flight_plan_when_call_getOneFlightFlightPlanByPlanId() {
        String executionDate = "2020-10-01";
        long planId = 3311;
        Pilot stubPilot = mock(Pilot.class);
        when(stubPilot.getId()).thenReturn(ACCOUNT_ID);

        when(accountService.getPilotByAccountId(ACCOUNT_ID)).thenReturn(stubPilot);

        FlightPlan flightPlan = mock(FlightPlan.class);
        when(flightPlan.getId()).thenReturn(planId);

        when(flightPlanRepository.findByUavPilotAndExecutionDateAndId(any(), any(), anyLong())).thenReturn(Optional.ofNullable(flightPlan));
        Assert.assertEquals(planId, flightPlanService.getFlightPlanByPlanId(ACCOUNT_ID, executionDate, planId).getId());
    }

    @Test
    public void should_get_flight_plan_when_call_getFlightPlanBelongToUavRawData() {
        String executionDate = "2020-09-27";
        String time = "00:08:00";
        String macAddress = "0000000018021025";
        Uav uav = mock(Uav.class);
        when(uav.getId()).thenReturn((long) 2201);
        when(uavService.getUavIfExists(macAddress)).thenReturn(uav);

        FlightPlan flightPlan = mock(FlightPlan.class);
        when(flightPlan.getId()).thenReturn((long)3301);
        when(flightPlanRepository.findByMacAddressAndExecutionDateAndBetweenStartAndEndTime(any(),any(),any()))
                .thenReturn(Optional.of(flightPlan));

        assertEquals(flightPlan.getId(), flightPlanService.getFlightPlanBelongToUavRawData(macAddress, executionDate, time).getId());

    }

}
