package com.All_IN.media.live.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveDTO implements Serializable {

    String index;

    String type;

    String tsFileName;

    byte[] video;

}
