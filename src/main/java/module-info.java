module org.jaudiotagger {
  requires java.logging;
  requires java.desktop;
  requires org.tinylog.api;

  exports org.jaudiotagger;
  exports org.jaudiotagger.audio;
  exports org.jaudiotagger.logging;
  exports org.jaudiotagger.audio.mp3;
  exports org.jaudiotagger.audio.flac;
  exports org.jaudiotagger.audio.flac.metadatablock;
  exports org.jaudiotagger.audio.exceptions;
  exports org.jaudiotagger.audio.wav;
  exports org.jaudiotagger.audio.wav.chunk;
  exports org.jaudiotagger.audio.generic;
  exports org.jaudiotagger.tag;
  exports org.jaudiotagger.tag.flac;
  exports org.jaudiotagger.tag.wav;
  exports org.jaudiotagger.tag.id3;
  exports org.jaudiotagger.tag.id3.framebody;
  exports org.jaudiotagger.tag.id3.reference;
  exports org.jaudiotagger.tag.id3.valuepair;
  exports org.jaudiotagger.tag.reference;
}
