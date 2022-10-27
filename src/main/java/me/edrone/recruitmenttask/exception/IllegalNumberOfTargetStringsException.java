package me.edrone.recruitmenttask.exception;

public class IllegalNumberOfTargetStringsException extends RuntimeException {
    public IllegalNumberOfTargetStringsException() {
        super("Number of target strings is invalid or is doesn't match number of possible results.");
    }
}
