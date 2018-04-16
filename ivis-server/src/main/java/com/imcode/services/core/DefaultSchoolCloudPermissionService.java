package com.imcode.services.core;

import com.imcode.entities.Pupil;
import com.imcode.entities.School;
import com.imcode.entities.SchoolClass;
import com.imcode.entities.User;
import com.imcode.services.PupilService;
import com.imcode.services.SchoolCloudPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSchoolCloudPermissionService implements SchoolCloudPermissionService {

    private final PupilService pupilService;

    @Autowired
    public DefaultSchoolCloudPermissionService(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @Override
    public boolean hasUserApprovedAccess(final User user) {

        if (!user.hasRoles("ROLE_STUDENT")) {
            return false;
        }

        final Pupil pupil = pupilService.getPupilByPerson(user.getPerson());

        return isApprovedBySchool(pupil) && isApprovedBySchoolClass(pupil) && user.getSchoolCloudEnabled();
    }

    private boolean isApprovedBySchool(final Pupil pupil) {
        final School pupilSchool = pupil.getSchool();
        return pupilSchool == null || pupilSchool.getSchoolCloudEnabled();
    }

    private boolean isApprovedBySchoolClass(final Pupil pupil) {
        final SchoolClass schoolClass = pupil.getSchoolClass();
        return schoolClass == null || schoolClass.getSchoolCloudEnabled();
    }
}