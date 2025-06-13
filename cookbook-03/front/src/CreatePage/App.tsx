import { Input } from '@douyinfe/semi-ui';
import { ControlFeatureContext } from '@lark-project/js-sdk';
import axios from 'axios';
import React, { useCallback, useEffect } from 'react';

export default function App(props: ControlFeatureContext) {
  const { workItemId, UUID } = props;
  const [value, setValue] = React.useState<string>();

  useEffect(() => {
    if (!workItemId) {
      return;
    }
    axios
      .get(
        `https://byd.xiongdianpku.com/demo/getValueByWorkitemid?workItemId=${workItemId}`,
      )
      .then((res) => {
        setValue(res?.data?.data ?? '');
      });
  }, [workItemId]);

  const handleBlur = useCallback(() => {
    if (!workItemId && UUID) {
      axios.post('https://byd.xiongdianpku.com/demo/create/uuid', {
        uuid: UUID,
        value: value,
      });
    }
  }, [UUID, value, workItemId]);

  return (
    <Input
      value={value}
      placeholder={'请输入'}
      onChange={setValue}
      onBlur={handleBlur}
    />
  );
}
