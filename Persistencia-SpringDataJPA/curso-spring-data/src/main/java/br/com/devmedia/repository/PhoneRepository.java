package br.com.devmedia.repository;

import br.com.devmedia.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete from Phone p where p.number like ?1")
    int deleteByPhoneNumber(String number);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Phone p set p.number = ?1 where p.id = ?2")
    int setPhoneNumber(String number, Long id);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update Phone p set p.type = ?1 where p.id = ?2")
    int setPhoneNumber(Phone.TypePhone type, Long id);
}
