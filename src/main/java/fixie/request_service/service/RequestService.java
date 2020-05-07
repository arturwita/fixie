package fixie.request_service.service;

import fixie.request_service.dto.RequestDTO;
import fixie.request_service.entity.Request;
import fixie.request_service.entity.Status;
import fixie.request_service.exception.RequestNotFoundException;
import fixie.request_service.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request createRequest(Long customerId) {
        Request request = Request.builder()
                .customerId(customerId)
                .status(Status.DEFAULT_STATUS)
                .build();

        requestRepository.save(request);

        return request;
    }

    @Override
    public Request setRequestStatus(Long id, RequestDTO requestDTO) throws RequestNotFoundException {
        Optional<Request> requestOptional = requestRepository.findById(id);

        if (!requestOptional.isPresent()) throw new RequestNotFoundException();

        Request request = requestOptional.get();
        request.setStatus(requestDTO.status);

        requestRepository.save(request);

        return request;
    }
}
