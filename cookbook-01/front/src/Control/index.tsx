import { Spin } from '@douyinfe/semi-ui';
import React, { lazy, Suspense } from 'react';
import ReactDOM from 'react-dom';
import { createRoot } from 'react-dom/client';

const App = lazy(() => import('./App'));

export default async function main() {
  await window.JSSDK.shared.setSharedModules({
    React,
    ReactDOM,
  });

  const container = document.createElement('div');
  container.id = 'app';
  document.body.appendChild(container);
  const root = createRoot(container);

  const ctx = await window.JSSDK.control.getContext();

  root.render(
    <Suspense fallback={<Spin />}>
      <App {...ctx} />
    </Suspense>,
  );
}

const Dropdown = lazy(() => import('./Dropdown'));

export async function selectDropdown() {
  await window.JSSDK.shared.setSharedModules({
    React,
    ReactDOM,
  });

  const container = document.createElement('div');
  container.id = 'app';
  document.body.appendChild(container);
  const root = createRoot(container);

  root.render(
    <Suspense fallback={<Spin />}>
      <Dropdown />
    </Suspense>,
  );
}
