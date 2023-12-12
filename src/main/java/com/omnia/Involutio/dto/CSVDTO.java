package com.omnia.Involutio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CSVDTO {
    private String email;
    private int sentMessages;
    private int receivedMessages;
    private int recipientsCount;
    private int bccRecipientsCount;
    private int ccRecipientsCount;
    private int unreadMessages4Hours;
    private int daysBetweenReceiveAndRead;
    private int repliedMessages;
    private int outgoingMessageLength;
    private int messagesOutsideWorkingHours;
    private double receivedSentRatio;
    private double bytesReceivedSentRatio;
    private int unansweredQuestionCount;
}
