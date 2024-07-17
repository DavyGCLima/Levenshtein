package com.davylima.normaliser_job_title.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ComparableResult {

    @Builder.Default
    String input = "";

    @Builder.Default
    String comparedWith = "";

    @Builder.Default
    Double similarity = 0.0;
}
