package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final TagRepository tagRepository;
    private final WritingRepository writingRepository;

    @Transactional
    public List<Writing> searchResults(List<DiseaseName> diseaseNames){
        List<Writing> writings = writingRepository.findAll();
        Map<Writing, Integer> map = new HashMap<Writing, Integer>();
        int frequency;
        for(int i = 0; i < writings.size(); i++){
            List<Tag> tagsFromWriting = tagRepository.findListByWriting(writings.get(i));
            frequency = 0;
            for(int j = 0; j < tagsFromWriting.size(); j++) {
                Tag jInTagsFromWriting = tagsFromWriting.get(j);
                if(diseaseNames.contains(jInTagsFromWriting.getDiseaseName()))
                    frequency++;
            }
            if(frequency > 0)
                map.put(writings.get(i), frequency);
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
