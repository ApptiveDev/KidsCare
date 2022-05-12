package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final TagRepository tagRepository;
    private final WritingRepository writingRepository;

    @Transactional
    public List<Writing> searchResults(List<Tag> tags){
        List<Writing> writings = writingRepository.findAll(); //임시
        ArrayList<Writing> results; // array로 해도 되는지 모르겠네?
        // contains를 이용하여 개수 체크 // 근데 이러면 이중 for문이라서 시간이 많이 걸릴거 같은데??
        // 1. 모든 writing 불러오고,
        // 2. 각각의 wirting에 tags가 몇개 포함되는지 체크
        // 카운팅 없는것들 일단 다 버리고
        // 카운팅 되는것들 정렬해서 주면 될거같은데,
        //


        // 화면에 띄울때, 태그 어떻게 또 넘겨줘야하지????
        // 그냥 writing 넘겨주고, 프론트에서 타임리프로 검색한 태그가 있으면 표시하고 없으면 표시 안하기
        // 근데 이렇게하면 표시O 표시O 표시X 표시X가 되는게 아니라, 표시X 표시O 표시X 표시O로 됨.
        // 아니면 해당되는것만 표시해도 되는데, 살짝 애매?



        return writings;
    }
}
