package fixie.request_service.service;

import fixie.request_service.dto.RequestDTO;
import fixie.request_service.entity.Request;
import fixie.request_service.exception.RequestNotFoundException;

import java.util.List;

public interface IRequestService {

    List<Request> getRequests();

    Request createRequest(Long customerId);

    Request setRequestStatus(Long id, RequestDTO requestDTO)
            throws RequestNotFoundException;
}
