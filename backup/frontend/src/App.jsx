import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-blue-600 via-indigo-600 to-purple-600 p-4">
      <div className="w-full max-w-lg bg-white shadow-lg rounded-lg p-8">
        <h1 className="text-2xl font-bold text-gray-800 mb-6 text-center">
          FAQ TELEGRAM BOT - Ask Your Questions
        </h1>
        <form className="space-y-4">
          <textarea
            className="w-full h-32 p-4 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Type your question here..."
          />
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300"
          >
            Submit
          </button>
        </form>
        <div className="mt-6">
          <h2 className="text-xl font-semibold text-gray-700">Answers</h2>
          <div className="mt-2 p-4 border border-gray-200 rounded-lg bg-gray-50">
            <p className="text-gray-600">
              Answers to your questions will appear here!
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}

export default App
