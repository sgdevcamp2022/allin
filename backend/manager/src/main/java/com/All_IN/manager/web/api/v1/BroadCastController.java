package com.All_IN.manager.web.api.v1;

import com.All_IN.manager.mapper.broadCast.OnLiveBroadCastListDTO;
import com.All_IN.manager.service.braodCast.BroadCastService;
import com.All_IN.manager.web.response.ApiResponse;
import com.All_IN.manager.web.response.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/broadcast")
public class BroadCastController {

    private static String ManagerServerCode = "400";

    private final BroadCastService broadCastService;


    @GetMapping("/lives")
    public ApiResponse<ApiResponse.withData> onLiveList() {
        OnLiveBroadCastListDTO onLiveBroadCastListDTO = broadCastService.liveList();

        return ApiResponseGenerator.success(
            onLiveBroadCastListDTO,
            HttpStatus.OK,
            HttpStatus.OK.value() + ManagerServerCode,
            "on live list"
        );
    }

}
