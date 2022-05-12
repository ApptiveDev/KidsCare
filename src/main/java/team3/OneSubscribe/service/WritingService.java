package team3.OneSubscribe.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.WritingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;

    /**
     *
     * @param writing
     * true : 성공
     * false : 실패
     */
    public boolean insertWriting(Writing writing) {
        if (writingRepository.save(writing) != 0) {
            return true;
        }
        return false;
    }


    //TODO : 전부조회
    public List<Writing> findAll() {
        return writingRepository.findAll();
    }

    //TODO : 수정
    //TODO : 삭제


}
