package com.nutn.utm.model.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PredictResultListDto {

    List<PredictRequestBodyDto> predictResult;
}
