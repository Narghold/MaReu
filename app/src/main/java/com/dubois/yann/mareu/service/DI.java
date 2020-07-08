package com.dubois.yann.mareu.service;

/**
 *  Dependency injector to get instance of services
 */
public class DI {

    private static MeetingApiService meetingService = new MeetingService();
    private static FilterApiService filterService = new FilterService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return meetingService;
    }

    public static MeetingApiService getNewMeetingApiService() {
        return new MeetingService();
    }

    /**
     * Get an instance on @{@link FilterApiService}
     * @return
     */
    public static FilterApiService getFilterApiService(){
        return filterService;
    }

}
