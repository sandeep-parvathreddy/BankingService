package com.bsf.model.response;

import com.bsf.model.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private String transactionId;

    private List<AccountDTO> accounts;
}
