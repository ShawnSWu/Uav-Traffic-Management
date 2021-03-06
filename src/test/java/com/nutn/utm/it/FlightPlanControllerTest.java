package com.nutn.utm.it;

import com.nutn.utm.controller.FlightPlanController;
import com.nutn.utm.model.dto.form.FlightPlanApplicationForm;
import com.nutn.utm.model.dto.geojson.flightplan.FlightPlanWayPointsDto;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@ActiveProfiles("dev")
@Sql(scripts = {"classpath:clear.sql", "classpath:testData.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class FlightPlanControllerTest extends BaseMvcTest {

    private final String URL_PREFIX = "/flightPlan";

    @Autowired
    FlightPlanController fightPlanController;

    @Override
    protected Object controller() {
        return fightPlanController;
    }

    @Test
    public void should_return_new_flightplan_when_apply_successfully() throws Exception {
        String uavMacAddress = "0000000018021025";
        String expectedDate = "2090-01-01";
        String startTime = "22:59";
        String endTime = "23:59";
        int maxFlyingAltitude = 65;
        Double[][] coordinate = {
                {120.14236450195312, 23.161194613746826}, {120.1348114013672, 23.22935799291749},
                {120.21102905273436, 23.174451324926217}, {120.21377563476562, 23.240715176105464}
        };

        FlightPlanWayPointsDto flightPlanPathDTO = new FlightPlanWayPointsDto(coordinate);
        String description = "Test description";

        FlightPlanApplicationForm planFormDTO = FlightPlanApplicationForm.builder()
                .macAddress(uavMacAddress)
                .executionDate(expectedDate)
                .startTime(startTime)
                .endTime(endTime)
                .maxFlyingAltitude(maxFlyingAltitude)
                .flightPlanWayPointsDto(flightPlanPathDTO)
                .description(description)
                .build();
        mockMvc.perform(
                jsonRequest(post(URL_PREFIX), planFormDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("planId").isNotEmpty())
                .andExpect(jsonPath("macAddress").value(uavMacAddress))
                .andExpect(jsonPath("executionDate").value(expectedDate))
                .andExpect(jsonPath("startTime").value(startTime))
                .andExpect(jsonPath("endTime").value(endTime))
                .andExpect(jsonPath("maxFlyingAltitude").value(maxFlyingAltitude))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("flightPlanWayPoints").isNotEmpty());
    }

//    @Test
    public void should_return_modified_flightplan_when_modify_successfully() throws Exception {
        long planId = 3302;
        String uavMacAddress = "0000000018021025";
        String expectedDate = "2090-01-01";
        String startTime = "22:59";
        String endTime = "23:59";
        int maxFlyingAltitude = 65;
        Double[][] coordinate = {{120.296086, 22.628091}, {120.297104, 22.627854}, {120.29843, 22.627502},
                {120.2987, 22.626256}, {120.298982, 22.625441}};

        FlightPlanWayPointsDto flightPlanPathDTO = new FlightPlanWayPointsDto(coordinate);
        String description = "Test description";

        FlightPlanApplicationForm planFormDTO = FlightPlanApplicationForm.builder()
                .macAddress(uavMacAddress)
                .executionDate(expectedDate)
                .startTime(startTime)
                .endTime(endTime)
                .maxFlyingAltitude(maxFlyingAltitude)
                .flightPlanWayPointsDto(flightPlanPathDTO)
                .description(description)
                .build();

        mockMvc.perform(
                jsonRequest(put(URL_PREFIX + "/id/" + planId), planFormDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("planId").isNotEmpty())
                .andExpect(jsonPath("macAddress").value(uavMacAddress))
                .andExpect(jsonPath("executionDate").value(expectedDate))
                .andExpect(jsonPath("startTime").value(startTime))
                .andExpect(jsonPath("endTime").value(endTime))
                .andExpect(jsonPath("maxFlyingAltitude").value(maxFlyingAltitude))
                .andExpect(jsonPath("description").value(description))
                .andExpect(jsonPath("flightPlanWayPoints").isNotEmpty());
    }

//    @Test
    public void should_return_all_flightPlan_id_when_delete_successfully() throws Exception {
        long planId = 3302;
        mockMvc.perform(
                delete(URL_PREFIX + "/id/" + planId))
                .andExpect(status().isOk())
                .andExpect(content().string("3302"));
    }

//    @Test
    public void should_return_all_flightPlan_of_date_when_query_by_date_successfully() throws Exception {
        String date = "2090-09-27";
        mockMvc.perform(get(URL_PREFIX + "/date/" + date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("type").isNotEmpty());
    }

    @Test(expected = NestedServletException.class)
    public void should_throw_exception_when_flightPlan_path_cross_forbid_area() throws Exception {
        String uavMacAddress = "0000000018021025";
        String expectedDate = "2090-01-01";
        String startTime = "22:59";
        String endTime = "23:59";
        int maxFlyingAltitude = 65;
        Double[][] coordinate = {{120.18733978271483, 23.009833292365016}, {120.23566246032715, 22.99434804948714}, {120.24544715881348, 23.008569257508213}};

        FlightPlanWayPointsDto flightPlanPathDTO = new FlightPlanWayPointsDto(coordinate);
        String description = "Test description";

        FlightPlanApplicationForm planFormDTO = FlightPlanApplicationForm.builder()
                .macAddress(uavMacAddress)
                .executionDate(expectedDate)
                .startTime(startTime)
                .endTime(endTime)
                .maxFlyingAltitude(maxFlyingAltitude)
                .flightPlanWayPointsDto(flightPlanPathDTO)
                .description(description)
                .build();

        mockMvc.perform(jsonRequest(post(URL_PREFIX), planFormDTO));

    }

}
