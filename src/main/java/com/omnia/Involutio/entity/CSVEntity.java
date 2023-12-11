package com.omnia.Involutio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Entity
@Data
@Slf4j
@NoArgsConstructor
public class CSVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fio;
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
