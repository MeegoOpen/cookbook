import React, { useCallback, useEffect, useState } from 'react';
import { Select, Button } from '@douyinfe/semi-ui';
import { Popover } from '@lark-project/ui-kit-plugin';
import { ControlFeatureContext } from '@lark-project/js-sdk';
import axios from 'axios';
import { OptionProps } from '@douyinfe/semi-ui/lib/es/select';

export default function App(props: ControlFeatureContext) {
  const { spaceId, workObjectId, workItemId } = props;
  const [optionList, setOptionList] = useState<OptionProps[]>([]);
  const [value, setValue] = useState<string | undefined>();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const getOptionList = async () => {
      // https://byd.xiongdianpku.com/webhook/getDemoList
      const res = await axios.get(
        'xxx',
      );
      setOptionList(
        (res?.data || []).map((item) => ({
          ...item,
          value: item.name,
          label: item.name,
        })),
      );
    };
    getOptionList();
  }, []);

  const handleUpdate = useCallback(() => {
    if (!value) {
      return;
    }
    setLoading(true);
    // https://byd.xiongdianpku.com/webhook/updateDescription
    axios
      .post('xxx', {
        project_key: spaceId,
        work_item_type_key: workObjectId,
        work_item_id: workItemId,
        value,
      })
      .then((res) => {
        setLoading(false);
        window.JSSDK.toast.success('修改成功');
      });
  }, [value, spaceId, workObjectId, workItemId]);

  const handleChange = useCallback((value) => {
    setValue(value);
  }, []);

  return (
    <div>
      <Popover
        position={'bottomLeft'}
        trigger={'click'}
        entry="selectDropdown"
        context={{
          optionList,
          value,
          onChange: handleChange,
        }}>
        <div style={{ display: 'inline-block' }}>
          <Select
            value={value}
            placeholder={'请选择'}
            style={{ width: 400 }}
            emptyContent={null}
            optionList={optionList}
            dropdownStyle={{ display: 'none' }}
          />
        </div>
      </Popover>
      <Button
        style={{ marginLeft: 12 }}
        loading={loading}
        disabled={!value}
        onClick={handleUpdate}>
        修改
      </Button>
    </div>
  );
}
