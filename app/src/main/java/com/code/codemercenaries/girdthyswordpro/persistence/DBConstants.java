package com.code.codemercenaries.girdthyswordpro.persistence;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joel Kingsley on 02-11-2018.
 */

public class DBConstants {

    //Local Database
    public static final String TABLE_VERSION = "version";
    public static final String TABLE_EN_KJV = "en_kjv";
    public static final String TABLE_TAM_ORG = "tam_org";
    public static final String TABLE_TELUGU = "telugu";
    public static final String TABLE_ORIYA = "oriya";
    public static final String TABLE_GREEK = "el_greek";
    public static final String TABLE_SPANISH = "es_rvr";
    public static final String TABLE_FRENCH = "fr_apee";

    public static final String V_KEY_ID = "id";
    public static final String V_KEY_NAME = "name";
    public static final String V_KEY_LANG = "lang";

    public static final String B_KEY_ID = "id";
    public static final String B_KEY_BOOK_NAME = "book_name";
    public static final String B_KEY_CHAP_NUM = "chapter_num";
    public static final String B_KEY_VERSE_NUM = "verse_num";
    public static final String B_KEY_VERSE_TEXT = "verse_text";
    public static final String B_KEY_MEMORY = "memory";
    public static final String B_KEY_READ = "read";

    public static final int CODE_NOT_ADDED = 0;
    public static final int CODE_ADDED = 1;
    public static final int CODE_MEMORIZED = 2;

    public static final int CODE_NOT_READ = 0;
    public static final int CODE_READ = 1;

    public static final int MASTERED_MIN_THRESHOLD_SCORE = 85;
    public static final int MAINTAIN_MIN_THRESHOLD_SCORE = 65;

    public static final int INITIAL_DEFAULT_SPACE = 1;
    public static final int MASTERED_MIN_THRESHOLD_SPACE = 2;

    public static final String NEXT_DATE_OF_REVIEW_NA = "NA";

    //Remote Database
    public static final String FIREBASE_TABLE_USERS = "users";
    public static final String FIREBASE_TABLE_CHUNKS = "chunks";
    public static final String FIREBASE_TABLE_SECTIONS = "sections";
    public static final String FIREBASE_TABLE_USER_BIBLE = "user_bible";
    public static final String FIREBASE_TABLE_GLOBAL_MESSAGES = "global_messages";
    public static final String FIREBASE_TABLE_GLOBAL_REPORTS = "global_reports";
    public static final String FIREBASE_TABLE_ADMIN_MESSAGES = "admin_messages";

    public static final String FIREBASE_C_KEY_NEXT_DATE_OF_REVIEW = "nextDateOfReview";
    public static final String FIREBASE_C_KEY_SPACE = "space";
    public static final String FIREBASE_C_MASTERED = "mastered";
    public static final String FIREBASE_UB_KEY_MEMORY = "memory";
    public static final String FIREBASE_U_KEY_EQUIPPED_SWORD = "equippedSword";
    public static final String FIREBASE_U_KEY_VERSES_MEMORIZED = "versesMemorized";
    public static final String FIREBASE_U_KEY_VERSES_ADDED = "versesAdded";
    public static final String FIREBASE_U_KEY_LAST_UPDATED_BY = "lastUpdatedBy";
    public static final String FIREBASE_U_KEY_OPT_OUT_OF_LB = "optOutOfLB";
    public static final String FIREBASE_S_KEY_VERSION_ID = "versionID";
    public static final String FIREBASE_GM_KEY_MESSAGE_ID = "messageID";
    public static final String FIREBASE_GM_KEY_MESSAGE_TEXT = "text";
    public static final String FIREBASE_GM_KEY_SOURCE_USER_ID = "sourceUserID";
    public static final String FIREBASE_GM_KEY_TIMESTAMP = "timestamp";

    public static final String DATE_FORMAT = "yyyy/MM/dd";

    //SharedPreferences
    public final static String SYSTEM_PREF = "system";
    public final static String SETTINGS_PREF = "settings";
    public final static String SET_CHUNK_SIZE = "chunk_size";
    public static final String REVIEW_TYPE = "review_type";
    public static final String MANUAL_REVIEW_TYPE = "manual";
    public static final String SPEECH_REVIEW_TYPE = "speech";
    public static final String REVIEW_CHUNK_ID = "review_chunk_id";
    public static final String REVIEW_FINAL_SCORE = "review_final_score";

    //Swords
    public static final String BRONZE_SWORD = "bronze_sword";
    public static final String SOLDIER_SWORD = "soldier_sword";
    public static final String PIRATE_SWORD = "pirate_sword";
    public static final String GLASS_SWORD = "glass_sword";
    public static final String GOLD_SWORD = "gold_sword";
    public static final String DIAMOND_SWORD = "diamond_sword";

    //Settings Items
    public static final int OPT_OUT_OF_LEADERBOARD = 0;

    public static final List<String> LANGUAGES_SUPPORTING_SPEECH_REVIEW = Arrays.asList(
            "en", "es", "el", "fr", "ta");
}
