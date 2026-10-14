package io.github.holydarktank2.mainframe.application.context;

public record CustomerReferenceData(
        String bankCode,
        String noc1ToTgic,
        String processBranchFromDb
) {
}
