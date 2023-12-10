package com.lcwd.userService.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource Not Found on server");
    }

    public ResourceNotFoundException(String msc) {

        super(msc);
    }
}