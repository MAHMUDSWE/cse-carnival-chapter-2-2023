import React, { useContext, useState } from 'react'
import { useMutation } from '@tanstack/react-query';

import ForgottenPassword from '../components/auth/ForgottenPassword';
import CreateNewAccountButton from '../components/auth/CreateNewAccountButton';
import LoginForm from '../components/form/LoginForm';

import { AuthService } from '../services/auth.service';
import { AuthContext } from '../contexts/AuthContext';
import { Link, useNavigate } from 'react-router-dom';
import { storeAccessToken } from '../utils/token.util';
import { toast } from 'react-toastify';
import JoinAsDoctor from '../components/auth/JoinAsDoctor';


export default function LoginPage() {

  const navigate = useNavigate();
  const { setIsLoggedIn } = useContext(AuthContext);
  const [loginError, setLoginError] = useState(null);

  const loginMutation = useMutation({
    mutationFn: AuthService.login,

    onSuccess: (data) => {
      storeAccessToken(data.access_token);
      setIsLoggedIn(!!data.access_token);
      toast.success("Login successful! Welcome back.");
      navigate("/home");
    },
    onError: (data) => {
      if (data.response.status == 503) {
        toast.error("Oops! Something went wrong. Please Try Again Later.");
      }
      else {
        setLoginError(data.response.data.message || data.response.statusText);
      }
    }
  });

  const onSubmit = async (credential) => {
    await loginMutation.mutateAsync(credential);
  }


  return (
    <div className='mt-1'>
      <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">

        <div className="w-96 p-8 bg-white rounded shadow-lg mt-2">

          <div className='inline-flex text-center gap-3 mb-3'>
            <p className="text-center text-2xl">Log in to ReachOut </p>

          </div>

          <LoginForm onSubmit={onSubmit} loginError={loginError} setLoginError={setLoginError} />

          <ForgottenPassword />

          {!window.location.href.endsWith('/login') &&
            <>
              <CreateNewAccountButton />
              <h1 className='text-center text-sm font-semibold mt-3'>OR</h1>
              <JoinAsDoctor />
            </>
          }
        </div>
      </div>
    </div>
  )
}