package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Activity;

import com.imcode.services.ActivityService;
import com.imcode.services.UserService;
import com.imcode.utils.IssueAttachmentFileUtil;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GenericValidator;
import com.imcode.validators.GenericValidator.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.Map;

import static com.imcode.validators.GenericValidator.*;

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

    @Autowired
    private UserService userService;

    @Override
    public Object create(@RequestBody @Valid Activity entity, BindingResult bindingResult, WebRequest webRequest) {
        ValidationUtils.invokeValidator(new GenericValidator("reportDay", "reportedBy"), entity, bindingResult);
        entity.setReportDay(new Date());
        entity.setReportedBy(StaticUtls.getCurrentUser(webRequest, userService).getPerson());
        return super.create(entity, bindingResult, webRequest);
    }

    @RequestMapping(value = "/attach/{id}", method = RequestMethod.POST)
    public String setAttachment(@PathVariable("id") Long activityId,
                              @RequestParam("file") CommonsMultipartFile attachment,
                              WebRequest webRequest) {
        IssueAttachmentFileUtil issueAttachmentFileUtil = new IssueAttachmentFileUtil();

        Activity activity = activityService.find(activityId);

        issueAttachmentFileUtil.deleteIfExcist(activity, servletContext);

        activity.setFileName(attachment.getOriginalFilename());
        activity = activityService.save(activity);

        if (!attachment.isEmpty() && activity != null) {

            issueAttachmentFileUtil.saveActivityAttachment(activity, attachment, servletContext);
        }

        return activity.getFileName();
    }


    @RequestMapping(value = "/attach/{id}", method = RequestMethod.GET)
    public void getAttachment(@PathVariable("id") Long activityId,
                              HttpServletResponse response,
                              WebRequest webRequest) {

        Activity activity = activityService.find(activityId);

        if (activity != null && activity.getFileName() != null) {
            IssueAttachmentFileUtil issueAttachmentFileUtil = new IssueAttachmentFileUtil();
            issueAttachmentFileUtil.saveActivityAttachmentInResponse(activity, response, servletContext);
        }


    }

//    @Override
//    protected Map<String, Map<GenericValidator.Constraint, String>> getFieldsConstraints() {
//        Map<String, Map<Constraint, String>> fields = super.getFieldsConstraints();
//
//        buildField(fields, "description",
//                new SimpleEntry<>(Constraint.NOT_NULL_OR_EMPTY, null),
//                new SimpleEntry<>(Constraint.MAX, "5"),
//                new SimpleEntry<>(Constraint.MIN, "78"),
//                new SimpleEntry<>(Constraint.REGEX, EMAIL_PATTERN),
//                new SimpleEntry<>(Constraint.REGEX, URL_PATTERN)
//        );
//
//        buildField(fields, "issue",
//                new SimpleEntry<>(Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        buildField(fields, "id",
//                new SimpleEntry<>(Constraint.NOT_NULL_OR_EMPTY, null)
//        );
//
//        return fields;
//    }
}
