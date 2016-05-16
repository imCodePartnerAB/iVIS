package com.imcode.controllers.restful;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Activity;
import com.imcode.services.ActivityService;
import com.imcode.utils.IssueAttachmentFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruslan on 5/12/16.
 */
@RestController
@RequestMapping("/v1/{format}/activities")
public class ActivityRestControllerImpl extends AbstractRestController<Activity, Long, ActivityService> {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/attach/{activity_id}", method = RequestMethod.POST)
    public String setAttachment(@PathVariable("activity_id") Long activityId,
                              @RequestParam("file") CommonsMultipartFile attachment,
                              WebRequest webRequest) {

        Activity activity = activityService.find(activityId);
        activity.setFileName(attachment.getOriginalFilename());
        activity = activityService.save(activity);

        if (!attachment.isEmpty() && activity != null) {
            IssueAttachmentFileUtil issueAttachmentFileUtil = new IssueAttachmentFileUtil();
            issueAttachmentFileUtil.saveActivityAttachment(activity, attachment, servletContext);
        }

        return activity.getFileName();
    }


    @RequestMapping(value = "/attach/{activity_id}", method = RequestMethod.GET)
    public void gatAttachment(@PathVariable("activity_id") Long activityId,
                              HttpServletResponse response,
                              WebRequest webRequest) {

        Activity activity = activityService.find(activityId);

        if (activity != null) {
            IssueAttachmentFileUtil issueAttachmentFileUtil = new IssueAttachmentFileUtil();
            issueAttachmentFileUtil.saveActivityAttachmentInResponse(activity, response, servletContext);
        }


    }


}
