import styles from "./ArticleForm.module.css";
import { useState } from "react";
import articlesApi from "../api/articlesApi";

const INITIAL_FORM_DATA = {
  title: "",
  content: "",
  file: null,
};

export default function ArticleForm({ fetchArticles }) {
  const [inputData, setInputData] = useState(INITIAL_FORM_DATA);

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setInputData((prev) => ({ ...prev, [name]: value }));
  };

  const resetForm = () => {
    setInputData(INITIAL_FORM_DATA);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await articlesApi.postArticle(inputData);
      fetchArticles();
      resetForm();
    } catch (error) {
      console.error("ERROR : ", error);
    }
  };

  return (
    <div className={styles.articleFormContainer}>
      <form onSubmit={handleSubmit} className={styles.form}>
        <input
          type="text"
          id="title"
          name="title"
          value={inputData.title}
          onChange={handleFormChange}
          placeholder="title 입력"
          className={styles.input}
        />
        <textarea
          id="content"
          name="content"
          value={inputData.content}
          onChange={handleFormChange}
          placeholder="content 입력"
          className={styles.textarea}
        />
        <button type="submit" className={styles.button}>
          Article 생성
        </button>
      </form>
    </div>
  );
}
