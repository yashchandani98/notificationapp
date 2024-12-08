package com.bitgoassesment.notificationApp.service;

import com.bitgoassesment.notificationApp.constants.Messages;
import com.bitgoassesment.notificationApp.dto.*;
import com.bitgoassesment.notificationApp.entity.Notification;
import com.bitgoassesment.notificationApp.entity.NotificationStatus;
import com.bitgoassesment.notificationApp.repository.NotificationRepository;
import com.bitgoassesment.notificationApp.utils.NotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bitgoassesment.notificationApp.constants.Messages.notificationSubject;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public CreateNotificationResponseBody createNotification(CreateNotificationRequestBody requestBody) {

        Notification notification = notificationRepository.createNotification(
                requestBody.getCurrentPrice(),
                requestBody.getMarketVolume(),
                requestBody.getIntradayHighPrice(),
                requestBody.getMarketCap()
        );

        CreateNotificationResponseBody instance = CreateNotificationResponseBody
                .builder()
                .id(notification.getId())
                .message(Messages.successMessage)
                .build();
        return instance;
    }

    public ListNotificationsResponseBody listNotifications(ListNotificationsRequestBody requestBody) {

        NotificationStatus status;
        switch (requestBody.getStatus()){
            case "Outstanding":
                status = NotificationStatus.OUTSTANDING;
                break;
            case "Sent":
                status = NotificationStatus.SENT;
                break;
            case "Failed":
                status = NotificationStatus.FAILED;
                break;
            default:
                status = null;
        }
        List<Notification> notifications = notificationRepository.listNotifications(
                status
        );

        ListNotificationsResponseBody instance = ListNotificationsResponseBody
                .builder()
                .notifications(notifications)
                .message(Messages.successMessage)
                .build();
        return instance;
    }

    public SendNotificationResponseBody sendNotification(SendNotificationRequestBody requestBody) {
        Notification notification = notificationRepository.getNotificationById(requestBody.getNotificationId());

        if(notification == null){
            return SendNotificationResponseBody
                    .builder()
                    .message(Messages.notFoundMessage)
                    .build();
        }

        NotificationUtils.sendEmailNotification(requestBody.getEmail(),
                notificationSubject,
                String.format(Messages.notificationMessage,
                        notification.getCurrentPrice(),
                        notification.getMarketVolume(),
                        notification.getIntraDayHighPrice(),
                        notification.getMarketCap()
                ));

        notificationRepository.updateNotificationStatus(notification.getId(), NotificationStatus.SENT);

        return SendNotificationResponseBody.builder()
                .message(Messages.successMessage)
                .build();
    }
    public DeleteNotificationResponseBody deleteNotification(String notificationId) {
        Notification notification = notificationRepository.getNotificationById(notificationId);

        if(notification == null){
            return DeleteNotificationResponseBody
                    .builder()
                    .message(Messages.notFoundMessage)
                    .build();
        }
        notificationRepository.updateNotificationStatus(notificationId, NotificationStatus.DELETED);
        return DeleteNotificationResponseBody
                                    .builder()
                                    .message(Messages.successMessage)
                                    .build();
    }

}
