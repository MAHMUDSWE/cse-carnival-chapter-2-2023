package com.reachout.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reachout.backend.entity.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor_type", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class DoctorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
}
