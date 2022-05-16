package team3.OneSubscribe.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.AnswerRepository;
import team3.OneSubscribe.repository.WritingRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;
    private final AnswerRepository answerRepository;

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

    public List<Writing> sequenceByAnswerNumber(){
        List<Writing> writings = writingRepository.findAll();
        Map<Writing, Integer> map = new HashMap<Writing, Integer>();
        int answerNumber;

        if(writings.size() == 0){
            return writings;
        }
        else {
            for(int i = 0; i < writings.size(); i++) {
                answerNumber = answerRepository.findAnswerNumberByWriting(writings.get(i));
                map.put(writings.get(i), answerNumber);
            }

            List<Map.Entry<Writing, Integer>> entryList = new LinkedList<>(map.entrySet());
            entryList.sort(Map.Entry.comparingByValue());

            List<Writing> results = new LinkedList<>();
            for(int i = entryList.size(); i > 0; i--){
                results.add(entryList.get(i-1).getKey());
            }

            return results;
        }
    }

    public List<Writing> sequenceByAnswerNumberForAWeek(){
        List<Writing> writings = sequenceByAnswerNumber();
        List<Writing> writingsForSending = new LinkedList<>();
        if(writings.size() == 0){
            return writings;
        }
        else{
            List<Writing> results = new LinkedList<>();
            for(int i = 0; i < writings.size(); i++) {
                Writing writing = writings.get(i);
                if(LocalDateTime.now().minusDays(7).isBefore(writing.getCreateDate())){
                    writingsForSending.add(writing);
                }
            }

            if(writingsForSending.size() == 0){
                return null;
            }
            else{
                return writingsForSending;
            }
        }

    }


}
