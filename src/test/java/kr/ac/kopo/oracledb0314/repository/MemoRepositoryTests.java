package kr.ac.kopo.oracledb0314.repository;

import kr.ac.kopo.oracledb0314.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){

        System.out.println(memoRepository.getClass().getName());
    }

    // MemoRepository의 save/Memo Entity 객체의 참조값을 호출해서 insert한다.
    @Test
    public void testInsertDummies(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Dummy Data Test" + i).build();
            memoRepository.save(memo);
        });
    }

    //MemoRepository의 findById(Memo Entity 객체의 Id로 설정된 필드값)를 호출해서 select한다.
    //findById()호출되면 바로 select문을 실행한다.
    //findById()는 NULLPointerException이 발생되지 않도록 Null 체크를 한다.
    @Test
    public void testSelect(){
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=============================================");

        if (result.isPresent()){
            Memo memo = result.get();

            System.out.println(memo);
        }
    }

    //MemoRepository의 getOne(Memo Entity 객체의 Id로 설정된 필드값)를 호출해서 select한다.
    //getOne()은 호출되면 바로 실행되지 않고 Memo Entity가 필요할 때 select를 실행한다.
    @Test
    @Transactional
    public void testSelect2(){
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("=============================================");

        System.out.println(memo);
    }

    // MemoRepository의 save/Memo Entity 객체의 참조값을 호출해서 update한다.
    // save()는 호출되면 먼저 select를 하기 때문에 기존에 Entity가 있을 때는 update를 실행한다.
    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(95L).memoText("Update Dummy Data 95").build();

        Memo memo1 = memoRepository.save(memo);

        System.out.println(memo1);
    }

    // MemoRepository의 deleteById(Memo Entity의 mno 값을) 호출해서 delete한다.
    @Test
    public void testDelete(){
        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){
        //한 페이지당 10개의 Entity = 10개의 행을 뜻함.
        Pageable pageable = PageRequest.of(1, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        for (Memo memo : result.getContent()){
            System.out.println(memo);
        }
    }
}
