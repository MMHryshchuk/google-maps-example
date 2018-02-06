package com.mmhdev.devcv.domain.api.response;

import com.mmhdev.devcv.domain.api.dto.SearchPlaceResultDto;

import java.util.List;

/**
 * Created by on 13.12.16.
 */

public class PlaceResponse {

    List<SearchPlaceResultDto> results;
    String status;

    public PlaceResponse() {
    }


    public List<SearchPlaceResultDto> getResult() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
