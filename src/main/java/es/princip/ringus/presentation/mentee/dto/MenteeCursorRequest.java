package es.princip.ringus.presentation.mentee.dto;

public record MenteeCursorRequest(
    boolean suggested,
    Long cursor
) {
    public boolean isSuggested(){
        return suggested();
    }
}
