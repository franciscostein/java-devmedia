package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService {

    @Autowired
    private AvatarRepository repository;

    @Transactional(readOnly = false)
    public void saveOrUpdate(Avatar avatar) {

        repository.save(avatar);
    }

    public Avatar getAvatarByUpload(MultipartFile file) {

        Avatar avatar = new Avatar();

        if (file != null && file.getSize() > 0) {

            try {

                avatar.setTitulo(file.getOriginalFilename());
                avatar.setTipo(file.getContentType());
                avatar.setAvatar(file.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return avatar;
    }
}
