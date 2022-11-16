package edu.max.bstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Image {
    private String id;
    private String fileName;
}
