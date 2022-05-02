package team3.OneSubscribe.domain;

public enum DiseaseName {
    // 대문자로 적어야할거 같은데, 일단은 소문자
    /**
     *소화
     *: 복통(abdominalPain), 설사(diarrhea), 영양질환(nutritionalDisease)
     *
     *호흡
     *: 비염(rhinitis), 폐렴(pneumonia), 천식(asthma)
     *
     *피부
     *: 알레르기(allergic), 아토피(atopy)
     *
     *기타
     *두통(cephalalgia), 중이염(otitisMedia), 축농증(empyema)
     */

    abdominalPain, diarrhea, nutritionalDisease,
    rhinitis, pneumonia, asthma,
    allergic, atopy,
    cephalagia, otitisMedia, empyema
}
