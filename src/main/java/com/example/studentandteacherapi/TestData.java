package com.example.studentandteacherapi;

import com.example.studentandteacherapi.domain.Vinyl;
import com.example.studentandteacherapi.domain.Artist;
import com.example.studentandteacherapi.repository.VinylRepository;
import com.example.studentandteacherapi.repository.ArtistRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements ApplicationRunner {

  private VinylRepository vinylRepository;
  private ArtistRepository artistRepository;

  public TestData(VinylRepository vinylRepository, ArtistRepository artistRepository) {
    this.vinylRepository = vinylRepository;
    this.artistRepository = artistRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    Vinyl testVinyl = Vinyl.builder()
        .name("Andry")
        .surname("Kowalski")
        .email("corsar@gmail.com")
        .age(20)
        .course("IT")
        .build();
    vinylRepository.save(testVinyl);

    testVinyl = Vinyl.builder()
        .name("Jonny")
        .surname("Witewski")
        .email("witold@mail.com")
        .age(20)
        .course("HR")
        .build();
    vinylRepository.save(testVinyl);

    testVinyl = Vinyl.builder()
        .name("Tommy")
        .surname("Java")
        .email("java@mail.ua")
        .age(20)
        .course("IT")
        .build();
    vinylRepository.save(testVinyl);

    Artist artist = Artist.builder()
        .name("Andy")
        .surname("Smith")
        .email("java@mail.ua")
        .age(20)
        .course("IT")
        .build();
    artistRepository.save(artist);

    artist = Artist.builder()
        .name("Madison")
        .surname("Jones")
        .email("java@gmail.com")
        .age(20)
        .course("IT")
        .build();
    artistRepository.save(artist);

    artist = Artist.builder()
        .name("George")
        .surname("Davies")
        .email("spring@mail.com")
        .age(20)
        .course("IT")
        .build();

    artistRepository.save(artist);
  }
}

