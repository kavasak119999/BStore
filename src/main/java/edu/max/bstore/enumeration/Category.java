package edu.max.bstore.enumeration;

public enum Category {
    FANTASY("CATEGORY_FANTASY"),
    COMIC("CATEGORY_COMIC"),
    DETECTIVE("CATEGORY_DETECTIVE"),
    ROMANCE("CATEGORY_ROMANCE"),
    ADVENTURE("CATEGORY_ADVENTURE");

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
