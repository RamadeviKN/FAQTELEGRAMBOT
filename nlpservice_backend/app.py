from flask import Flask, request, jsonify
import spacy

# Load spaCy model
nlp = spacy.load("en_core_web_md")

app = Flask(__name__)

@app.route('/find_best_answer', methods=['POST'])
def find_best_answer():
    try:
        data = request.get_json()
        user_query = data['user_query']
        questions = data['questions']  # List of dictionaries with 'question_text' and 'answer'

        # Process the user query
        user_query_doc = nlp(user_query)

        # Initialize variables to track the best match
        best_match = None
        highest_similarity = 0.0

        # Loop through the list of questions
        for q in questions:
            question_text = q['question_text']
            question_doc = nlp(question_text)
            similarity = user_query_doc.similarity(question_doc)

            # If this question is more similar than the current best, update the best match
            if similarity > highest_similarity:
                highest_similarity = similarity
                best_match = q['answer']

        if best_match is None:
            return jsonify({"answer": None})
        
        return jsonify({"answer": best_match})

    except Exception as e:
        return jsonify({"error": str(e)}), 400


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
