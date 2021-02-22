package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.rmi.MarshalException;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //Transaction 시작

        try {

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            //flush -> commit, query
            List result = em.createNativeQuery("select MEMBER_ID, CITY, STREET, ZIPCODE, USERNAME from MEMBER", Member.class).getResultList();
            result.forEach(it -> System.out.println("it = " + it));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}

   /*
        try {
            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            result.forEach(it-> System.out.println("member.name = " + it.getName()));
            result.get(0).setName("HelloJPA ");
            //em.persist(result.get(0)); //필요없다! Transaction을 commit하기 직전에 변화를 감지하고 query를 만든다
            //em.remove(result.get(0));
            tx.commit();
        } catch (Exception e) {
            tx.rollback(); //Transaction rollback
        } finally {
            em.close(); //반드시 close를 해야한다
        }
   */


/*
            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

            //SQL: select * from Member 를 먼저 날리고 team이 있다는 것을 보고 (fetch type도 eager) team도 가져온다
            //SQL: select * from Team where TEAM_ID = xxx

            tx.commit();
*/


/*
            Member member = new Member();
            member.setUsername("user1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntitiy("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntitiy("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================== START ===================");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity
            Address old = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", old.getStreet(), old.getZipcode()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //address history 변경
            //기본적으로 collection들은 대상을 찾을 때 equals를 사용한다
            findMember.getAddressHistory().remove(new AddressEntitiy("old1", "street", "10000"));
            findMember.getAddressHistory().add(new AddressEntitiy("newCity1", "street", "zipcode"));

            tx.commit();

**/