package com.company.equals;

import java.util.List;
import java.util.Date;

public class Course {

    private final String uuid;

    private final String name;

    private final List<Session> sessions;

    public Course(final String uuid, final String name, final List<Session> sessions) {
        this.uuid = uuid;
        this.name = name;
        this.sessions = sessions;
    }

    private String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public boolean equals(final Object object) {

        if(!(object instanceof Course)){
            return false;
        }
        final Course that = (Course)object;
        if(that.uuid.equals(this.uuid)){
            return true;
        }
        return false;

    }

    public int hashCode() {

        if(this.uuid == null || uuid.length() == 0) return 0;
        int result = 0;
        for (int i = 0; i < uuid.length(); i++){
           result = uuid.charAt(i);
        }

        return result;

    }

    public class Session {

        private final Date startDate;

        Session(final Date startDate) {
            this.startDate = startDate;
        }

        Date getStartDate() {
            return this.startDate;
        }

        Course getCourse() {
            return Course.this;
        }

        @Override
        public boolean equals(final Object object) {


            if(!(object instanceof Course.Session)){
                return false;
            }

            Course.Session that = (Course.Session)object;

            if (that.getCourse().equals(this.getCourse())
                    & that.getStartDate().equals(this.getStartDate())) return true;

            return false;

        }
    }
}