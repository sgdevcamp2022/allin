package com.All_IN.manager.web.api.v1;

import com.All_IN.manager.mapper.room.RoomDTO;
import com.All_IN.manager.mapper.room.RoomInfoDTO;
import com.All_IN.manager.service.publisher.PublisherValidateIdType;
import com.All_IN.manager.service.publisher.PublisherValidateService;
import com.All_IN.manager.service.room.RoomService;
import com.All_IN.manager.web.dto.AllRoomResponse;
import com.All_IN.manager.web.dto.RoomInfoRequest;
import com.All_IN.manager.web.dto.RoomInfoResponse;
import com.All_IN.manager.web.response.ApiResponse;
import com.All_IN.manager.web.response.ApiResponseGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private static String ManagerServerCode = "400";


    private final RoomService roomService;

    private final PublisherValidateService publisherValidateService;


    @PostMapping
    public ApiResponse<ApiResponse.withCodeAndMessage> makeRoom(Long memberId, RoomInfoRequest roomInfoRequest) {
        Long validatePublisherId = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.MEMBER);
        roomService.save(validatePublisherId, roomInfoRequest);

        return ApiResponseGenerator.success(
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success make room"
        );
    }

    @GetMapping
    public ApiResponse<ApiResponse.withData> getRoomInfo(@RequestParam("roomId") Long roomId) {
        RoomInfoDTO roomInfoDTO = roomService.browseRoomInfo(roomId);

        return ApiResponseGenerator.success(
            new RoomInfoResponse(roomInfoDTO),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success browse roomInfo"
        );
    }


    @PutMapping
    public ApiResponse<ApiResponse.withCodeAndMessage> editRoom(Long roomId, RoomInfoRequest roomInfoRequest) {
        roomService.editRoomInfo(roomId, roomInfoRequest);

        return ApiResponseGenerator.success(
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success edit roomInfo"
        );
    }

    @GetMapping("/member/{memberId}")
    public ApiResponse<ApiResponse.withData> getMemberRoomInfo(@PathVariable("memberId") Long memberId) {
        Long validatePublisherId = publisherValidateService.validatePublisher(memberId, PublisherValidateIdType.MEMBER);

        RoomInfoDTO roomInfoDTO = roomService.browseMemberRoomInfo(validatePublisherId);

        return ApiResponseGenerator.success(
            new RoomInfoResponse(roomInfoDTO),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success member's roomInfo"
        );
    }

    @GetMapping("/all")
    public ApiResponse<ApiResponse.withData> browseRoomList() {
        List<RoomDTO> roomDTOS = roomService.browseRoomList();

        return ApiResponseGenerator.success(
            new AllRoomResponse(roomDTOS),
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "success browse all room list"
        );
    }

}
