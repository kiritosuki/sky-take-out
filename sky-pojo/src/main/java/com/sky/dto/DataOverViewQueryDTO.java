package com.sky.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Builder
public class DataOverViewQueryDTO implements Serializable {

    private LocalDateTime begin;

    private LocalDateTime end;

    @Override
    public String toString() {
        return "DataOverViewQueryDTO{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }

    public DataOverViewQueryDTO() {
    }

    public DataOverViewQueryDTO(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
