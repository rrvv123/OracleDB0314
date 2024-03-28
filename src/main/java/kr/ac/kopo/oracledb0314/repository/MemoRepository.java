package kr.ac.kopo.oracledb0314.repository;

import kr.ac.kopo.oracledb0314.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteMemoByMno(Long mno); // 특정 mno 값을 갖는 Memo Entity만 삭제할 때
    void deleteMemoByMnoLessThan(Long mno); // mno 미만인 Memo Entity들을 삭제할 때
    void deleteMemoByMnoLessThanEqual(Long mno); // mno 이하인 Memo Entity들을 삭제할 때
}
