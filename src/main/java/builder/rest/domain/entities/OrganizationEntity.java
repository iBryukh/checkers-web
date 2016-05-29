package builder.rest.domain.entities;

import builder.rest.domain.enums.OrganizationStatusEnum;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Entity
@Table(name = "ORGANIZATION")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrganizationStatusEnum status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = {CascadeType.REFRESH})
    private List<UserEntity> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.REFRESH})
    private List<ProjectEntity> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public OrganizationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrganizationStatusEnum status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationEntity that = (OrganizationEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != that.status) return false;
        if (users != null ? !users.equals(that.users) : that.users != null) return false;
//        return projects != null ? projects.equals(that.projects) : that.projects == null;
return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
     //   result = 31 * result + (projects != null ? projects.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrganizationEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", status=").append(status);
        sb.append(", users=").append(users);
       // sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
