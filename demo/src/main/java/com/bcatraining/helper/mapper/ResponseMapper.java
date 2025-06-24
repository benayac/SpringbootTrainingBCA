package com.bcatraining.helper.mapper;

import com.bcatraining.helper.dto.ApiResponse;

import java.util.List;

public interface ResponseMapper<M, R, S> {
    M requestToModel(R request);
    S modelToResponse(M model);
    List<S> listModelToListResponse(List<M> models);

    default ApiResponse<S> mapToApiResponse(M model) {
        var response = modelToResponse(model);
        return new ApiResponse<>(response);
    }

    default ApiResponse<List<S>> mapListModelToListResponse(List<M> models) {
        var response = listModelToListResponse(models);
        return new ApiResponse<>(response);
    }
}
