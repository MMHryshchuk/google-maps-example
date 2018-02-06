package com.mmhdev.devcv.domain.api.response;

import com.mmhdev.devcv.domain.api.dto.PlaceDetailsDto;
import com.mmhdev.devcv.domain.api.dto.SearchPlaceResultDto;

import java.util.List;

/**
 * Created by on 14.12.16.
 */

public class PlaceDetailsResponse {

    PlaceDetailsDto result;
    String status;

    public PlaceDetailsResponse() {
    }


    public PlaceDetailsDto getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }
}
