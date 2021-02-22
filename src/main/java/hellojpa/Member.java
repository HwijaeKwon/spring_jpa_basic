package hellojpa;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
//@Table(name = "USER") /*default는 class 이름에서 유추한 table에 저장한다*/
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //기간 period
    @Embedded
    private Period workPeriod;

    //주소 address
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") //예외적으로 컬럼명을 지정할 수 있다
    private Set<String> favoriteFoods = new HashSet<>();

/*
    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();
*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntitiy> addressHistory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntitiy> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntitiy> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
