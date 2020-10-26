package com.nutn.utm.it;

import com.nutn.utm.controller.MapGeographyController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
public class MapGeographyServiceControllerTest extends BaseMvcTest{

    @Autowired
    MapGeographyController mapInfoController;

    private final String URL_PREFIX = "/mapGeography";

    @Override
    protected Object controller() {
        return mapInfoController;
    }

    @Test
    public void testGetLimitAreaData() throws Exception {
        mockMvc.perform(get(URL_PREFIX+"/forbidArea/militaryCamp"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllowsFlyAreaData() throws Exception {
        mockMvc.perform(get(URL_PREFIX+"/restrictArea/airportNearby"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAirportCampLimitArea() throws Exception {
        mockMvc.perform(get(URL_PREFIX+"/forbidArea/airport"))
                .andExpect(status().isOk());
    }


}
