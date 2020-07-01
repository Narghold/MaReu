package com.dubois.yann.mareu.service;

/**
 *  Dependency injector to get instance of services
 */
public class DI {

    private static MeetingApiService meetingService = new MeetingService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return meetingService;
    }

    /**
     *  Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     *  @return
     */
    public static MeetingApiService getNewInstanceMeetingApiService() {
        return new MeetingService();
    }
}
