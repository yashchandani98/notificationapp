package com.bitgoassesment.notificationApp.controller;

import com.bitgoassesment.notificationApp.dto.*;
import com.bitgoassesment.notificationApp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications/")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping(value = "/create", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateNotificationResponseBody> createNotifications(@RequestBody CreateNotificationRequestBody requestBody){
        CreateNotificationResponseBody instance = notificationService.createNotification(requestBody);
        return ResponseEntity.ok(instance);
    }

    @PostMapping(value = "/sendNotification", produces = "application/json")
    public ResponseEntity<SendNotificationResponseBody> sendNotifications(@RequestBody SendNotificationRequestBody requestBody){
        SendNotificationResponseBody instance = notificationService.sendNotification(requestBody);
        return ResponseEntity.ok(instance);
    }

    @PostMapping(value = "/list", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListNotificationsResponseBody> sendNotifications(@RequestBody ListNotificationsRequestBody requestBody){
        ListNotificationsResponseBody instance = notificationService.listNotifications(requestBody);

        return ResponseEntity.ok(instance);
    }

    @DeleteMapping(value ="/delete/{id}", produces = "application/json")
    public ResponseEntity<DeleteNotificationResponseBody> deleteNotifications(@PathVariable String id){
        DeleteNotificationResponseBody instance = notificationService.deleteNotification(id);
        return ResponseEntity.ok(instance);
    }

}
