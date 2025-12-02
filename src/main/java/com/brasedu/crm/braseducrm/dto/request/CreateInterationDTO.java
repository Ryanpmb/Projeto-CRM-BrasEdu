package com.brasedu.crm.braseducrm.dto.request;

import com.brasedu.crm.braseducrm.enums.InterationType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateInterationDTO {
    int opportunityId;
    InterationType type;
    String description;
}
