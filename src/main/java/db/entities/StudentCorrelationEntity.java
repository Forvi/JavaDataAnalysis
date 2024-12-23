package db.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_correlation_entity")
public class StudentCorrelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_correlation_values", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "correlation_value")
    private List<Double> correlationsData;

    public StudentCorrelationEntity() {}

    public StudentCorrelationEntity(String name, List<Double> correlationsData) {
        this.name = name;
        this.correlationsData = correlationsData;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Double> getCorrelationsData() {
        return correlationsData;
    }

    public void setStudentName(String studentName) {
        this.name = studentName;
    }

    public void setCorrelationValue(List<Double> correlationsData) {
        this.correlationsData = correlationsData;
    }

    @Override
    public String toString() {
        return "StudentCorrelationEntity [id=" + id + ", name=" + name + ", correlationsData=" + correlationsData + "]";
    }
}
