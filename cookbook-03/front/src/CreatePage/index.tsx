import React, { lazy, Suspense } from 'react';
import ReactDOM from 'react-dom';
import { createRoot } from 'react-dom/client';
import login from '../login';
import { Spin } from '@douyinfe/semi-ui';

const App = lazy(() => import('./App'));

export default async function main() {
  await window.JSSDK.shared.setSharedModules({
    React,
    ReactDOM,
  });

  const container = document.createElement('div');
  container.id = 'app';
  document.body.appendChild(container);

  await login();

  const ctx = await window.JSSDK.control.getContext();
  const root = createRoot(container);
  root.render(
    <Suspense fallback={<Spin />}>
      <App {...ctx} />
    </Suspense>,
  );
}
